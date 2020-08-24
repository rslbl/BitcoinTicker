package kotlinversion

class Node<T>(item: T) {
  var value: T = item
  var next: Node<T>? = null
  var previous: Node<T>? = null
}

class LinkedList<T> {
  private var head: Node<T>? = null

  fun first(): Node<T>? = head
  fun last(): Node<T>? {
    var node: Node<T>? = head ?: return null
    while (node?.next != null) {
      node = node.next
    }
    return node
  }

  fun append(value: T) {
    val newNode = Node(value)
    val lastNode = this.last()
    if (lastNode != null) {
      newNode.previous = lastNode
      lastNode.next = newNode
    } else {
      head = newNode
    }
  }

  fun printNodes(): String {
    var text = ""
    var node = head
    while (node != null) {
      text += "${node.value}"
      node = node.next
      if (node != null) {
        text += " --> "
      }
    }
    return text
  }

  fun sumOfNodes(): Int {
    val node: Node<T>? = head ?: return 0
    var count = 1
    while (node?.next != null) {
      count += 1
    }
    return count
  }

  private fun deleteNode(node: Node<T>): T {
    val prev = node.previous
    val next = node.next
    if (prev != null) {
      prev.next = next
    } else {
      head = next
    }
    next?.previous = prev
    node.previous = null
    node.next = null
    return node.value
  }

  fun removeLast(): T? {
    val last = this.last() ?: return null
    return deleteNode(last)
  }

  fun removeAtIndex(index: Int): T? {
    val node = nodeAtIndex(index) ?: return null
    return deleteNode(node)
  }

  fun removeAll() {
    head = null
  }

  fun nodeAtIndex(index: Int): Node<T>? {
    if (index >= 0) {
      var node = head
      var i = index
      while (node != null) {
        if (i == 0) return node
        i -= 1
        node = node.next
      }
    }
    return null
  }
}

fun main(args: Array<String>) {
  val list = LinkedList<String>()
  list.append("One")
  list.append("Two")
  list.append("Three")
  list.append("Four")
  list.append("Five")

  println("First Item: ${list.first()?.value}")
  println("Second Item: ${list.first()?.next?.value}")

  println("All Nodes: ${list.printNodes()}")
  println("Sum Of Nodes: ${list.sumOfNodes()}")
  println("Node at Index: ${list.nodeAtIndex(3)}")
}
