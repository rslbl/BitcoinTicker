package net.resulbal.bitcointicker.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import net.resulbal.bitcointicker.Constants.FAVORITES
import net.resulbal.bitcointicker.Constants.USERS
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

  fun initCurrentUserIfFirstTime(onComplete: () -> Unit) {
    currentUserDocRef.get().addOnSuccessListener { documentSnapshot ->
      if (!documentSnapshot.exists()) {
        val user = User(
          name = FirebaseAuth.getInstance().currentUser?.displayName ?: "",
          bio = "",
          profilePicture = null
        )
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
    if (bio.isNotBlank()) userFieldMap["bio"] = bio
    if (profilePicture != null) userFieldMap["profilePicture"] = profilePicture

    currentUserDocRef.update(userFieldMap).addOnSuccessListener { onSuccess() }
  }

  fun updateFavorites(coinId: String, onComplete: () -> Unit) {
    currentUserDocRef.collection(FAVORITES)
      .document(coinId)
      .get()
      .addOnSuccessListener {
        if (it.exists()) {
          Timber.e("Document was here")
        } else {
          Timber.e("Document not found")
        }
      }
  }

}