package net.resulbal.bitcointicker.util

import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import net.resulbal.bitcointicker.Constants.FAVORITES
import net.resulbal.bitcointicker.Constants.USERS
import net.resulbal.bitcointicker.data.model.Coin
import net.resulbal.bitcointicker.data.model.User
import timber.log.Timber

/**
 * Created by rslbl on 2020-08-20.
 */

object FirestoreUtil {

  private val firestoreInstance: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

  private val currentUserDocRef: DocumentReference
    get() = firestoreInstance.document(
      "$USERS/${
        FirebaseAuth.getInstance().currentUser?.uid
          ?: throw NullPointerException("UID is null")
      }"
    )

  fun removeListener(registration: ListenerRegistration) = registration.remove()

  fun initCurrentUserIfFirstTime(onComplete: () -> Unit) {
    currentUserDocRef.get().addOnSuccessListener { documentSnapshot ->
      if (!documentSnapshot.exists()) {
        val user = User(name = FirebaseAuth.getInstance().currentUser?.displayName ?: "")
        currentUserDocRef.set(user).addOnSuccessListener { onComplete() }
      } else {
        onComplete()
      }
    }
  }

  fun updateCurrentUser(
    name: String = "",
    bio: String = "",
    profilePicture: String? = null,
    onSuccess: () -> Unit
  ) {
    val userFieldMap = mutableMapOf<String, Any>()
    if (name.isNotBlank()) userFieldMap["name"] = name

    currentUserDocRef.update(userFieldMap).addOnSuccessListener { onSuccess() }
  }

  fun getProfile(onSuccess: (User) -> Unit) {
    currentUserDocRef
      .get()
      .addOnSuccessListener { documentSnapshot ->
        if (!documentSnapshot.exists()) {
          onSuccess(User(""))
        } else {
          documentSnapshot.toObject(User::class.java)?.let { user -> onSuccess(user) }
        }
      }
  }

  fun logout(context: Context, onSuccess: () -> Unit) {
    AuthUI.getInstance()
      .signOut(context)
      .addOnSuccessListener { onSuccess() }
  }

  fun addOrDeleteFavorites(coin: Coin, onComplete: () -> Unit) {
    currentUserDocRef.collection(FAVORITES)
      .document(coin.id)
      .get()
      .addOnSuccessListener {
        if (it.exists()) {
          currentUserDocRef.collection(FAVORITES)
            .document(coin.id)
            .delete()
            .addOnSuccessListener { onComplete() }
        } else {
          val newCoin = coin.copy(favorite = !coin.favorite)
          currentUserDocRef.collection(FAVORITES)
            .document(coin.id)
            .set(newCoin)
            .addOnSuccessListener { onComplete() }
        }
      }
  }

  fun getFavoriteList(onListen: (List<Coin>) -> Unit): ListenerRegistration {
    return currentUserDocRef.collection(FAVORITES)
      .addSnapshotListener { querySnapshot, firestoreException ->
        if (firestoreException != null) {
          Timber.e("Firestore exception: $firestoreException")
          return@addSnapshotListener
        }

        val items = mutableListOf<Coin>()
        querySnapshot?.documents?.forEach { documentSnapshot ->
          documentSnapshot.toObject(Coin::class.java)?.let { coin ->
            items.add(coin)
          }
        }
        onListen(items)
      }
  }

}