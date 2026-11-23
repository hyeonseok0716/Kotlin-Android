package com.androidbook.bookmarket.chapter02

internal fun objectExamples(output: (String) -> Unit) {
    output("[객체 지향 프로그래밍]")
    val myObj = Member()
    myObj.name = "홍길동"
    myObj.age = 20
    myObj.gender = 'M'
    myObj.printMemberInfo(output)
    val myEmp = Employee(202201, "홍길순", output)
    output("Employee: ${myEmp.id}, ${myEmp.name}")
    val myPerson = Person("홍길순", 20, output)
    output("Person: ${myPerson.name}, ${myPerson.age}")
    val myAnimal = Animal()
    val myDog = Dog()
    output("Animal 클래스: ${myAnimal.sound()}")
    output("Dog 클래스: ${myDog.sound()}")
    val interfaceDog = Dog2()
    output("인터페이스 sound: ${interfaceDog.sound()}")
    output("인터페이스 sleep: ${interfaceDog.sleep()}")
    val abstractDog = Dog3()
    output("추상 클래스 sound: ${abstractDog.sound()}")
    val person = PersonData("홍길순", 30)
    output(person.toString())
    val myDog4 = Dog4(output)
    myDog4.printNames()
    myDog4.callSoundMethods()
}

private class Member {
    var name: String = ""
    var age: Int = 0
    var gender: Char = 'M'

    fun printMemberInfo(output: (String) -> Unit) {
        output("Member 이름: $name")
        output("Member 나이: $age")
        output("Member 성별: $gender")
    }
}

private class Employee(empId: Int, empName: String, output: (String) -> Unit) {
    val id: Int
    var name: String

    init {
        id = empId
        name = empName
        output("Employee 아이디: $id")
        output("Employee 이름: $name")
    }
}

private class Person {
    var name: String
    var age: Int

    constructor(pname: String, page: Int, output: (String) -> Unit) {
        this.name = pname
        this.age = page
        output("Person 이름: $name")
        output("Person 나이: $age")
    }
}

private open class Animal {
    open fun sound(): String {
        return "동물이 소리를 낸다"
    }
}

private class Dog : Animal() {
    override fun sound(): String {
        return "멍멍"
    }
}

private interface Animal2 {
    fun sound(): String

    fun sleep(): String {
        return "잠을 잔다"
    }
}

private class Dog2 : Animal2 {
    override fun sound(): String {
        return "멍멍"
    }
}

private abstract class Animal3 {
    abstract fun sound(): String
}

private class Dog3 : Animal3() {
    override fun sound(): String {
        return "멍멍"
    }
}

private data class PersonData(val name: String, val age: Int)

private open class Animal4(protected val output: (String) -> Unit) {
    open val name: String = "동물"

    init {
        output("Animal4 생성자 호출")
    }

    open fun sound(): String {
        return "동물 소리"
    }
}

private class Dog4(output: (String) -> Unit) : Animal4(output) {
    override val name: String = "강아지"

    init {
        output("Dog 생성자 호출")
    }

    override fun sound(): String {
        return "멍멍!"
    }

    fun printNames() {
        output("this.name = ${this.name}")
        output("super.name = ${super.name}")
    }

    fun callSoundMethods() {
        output("this.sound() 호출 결과: ${this.sound()}")
        output("super.sound() 호출 결과: ${super.sound()}")
    }
}
