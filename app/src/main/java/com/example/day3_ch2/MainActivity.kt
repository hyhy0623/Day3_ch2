package com.example.day3_ch2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

open class Super {  // 상속할 수 있게 open 키워드 이용
    class Sub: Super() {  // Super를 상속받아 Sub 클래스 선언

    }
}
// 매개변수 있을 때 상속
open class Super(name: String) {  // 상속할 수 있게 open 키워드 이용
    class Sub(name: String): Super(name) {  // Super를 상속받아 Sub 클래스 선언

    }
}
// 하위 클래스에 보조 생성자가 있다면 클래스 선언부에 작성하지 않아도 됨.
open class Super(name: String) {
}
class Sub: Super {
    constructor(name: Stirng): super(name) {

    }
}
// 상속 관계일 때, 상위 클래스의 멤버를 하위 클래스에서 사용 가능
open class Super {
    var superData = 10
    fun superFun() {
        println("i am superFun : $superData")
    }
}
class Sub: Super()
fun main() {
    val obj = Sub()
    obj.superData = 20
    obj.superFun()
}
// 오버라이딩 (상위 클래스에서 정의된 멤버를 하위 클래스에서 재정의
open class Super {
    open var someData = 10
    open fun someFun() {
        println("i am super class Function : $someData")
    }
}
class Sub: Super() {
    override var someData = 20
    override fun someFun() {
        println("i am super class Function : $someData")
    }
}
fun main() {
    val obj = Sub()
    obj.someFun()
}
// 접근 제한자 사용
open class Super {
    var publicData = 10
    protected var protectedData = 20
    private var privateData = 30
}
class Sub: Super() {
    fun subFun() {
        publicData++  // 성공
        protectedData++  // 성공
        privateData++  // 오류 (클래스 내부에서만)
    }
}
fun main() {
    val obj = Super()
    obj.publicData++  // 성공
    obj.protectedData++  // 오류 (상속 관계에서만)
    obj.privateData++  // 오류
}

// 데이터 클래스 선언
class NonDataClass(val name: String, val email: String, val age: Int)  // 일반 클래스
data class DataClass(val name: String, val email: String, val age: Int)  // data 클래스
fun main() {
    val non1 = NonDataClass("kkang", "a@a.com", 10)
    val non2 = NonDataClass("kkang", "a@a.com", 10)

    val data1 = DataClass("kkang", "a@a.com", 10)
    val data2 = DataClass("kkang", "a@a.com", 10)

    println("non data class equals : ${non1.equals(non2)}")  // false (객체 자체를 비교)
    println("data class equals : ${data1.equals(data2)}")  // true (객체의 데이터를 비교)
}

// 데이터 클래스의 equals() 함수
data class DataClass(val name: String, val email: String, val age: Int) {
    lateinit var address: String
    constructor(name: String, email: String, age: Int, address: String):
        this(name, email, age) {
        this.address = address
    }
}
fun main() {
    val obj1 = DataClass("kkang", "a@a.com", 10, "seoul")
    val obj2 = DataClass("kkang", "a@a.com", 10, "busan")
    println("obj1.equals(obj2) : ${obj1.equals(obj2)}")  // true (equals()함수는 주 생성자에 선언한 멤버 변수의 데이터만 비교 대상으로 삼음)

}

// 데이터 클래스의 toString() 함수
fun main() {
    class NonDataClass(val name: String, val email: String, val age: Int)
    data class DataClass(val name: String, val email: String, val age: Int)
    val non = NonDataClass("kkang", "a@a.com", 10)
    val data = DataClass("kkang", "a@a.com", 10)
    println("non data class toString : ${non.toString()}")  // 의미 없는 데이터 출력
    println("data class toString : ${data.toString()}")  // 객체가 포함하는 멤버 변수의 데이터를 출력 (주 생성자의 매개변수에 선언된 데이터만 출력)
}

// 오브젝트 클래스 사용 (익명 클래스를 만들 목적으로 사용 -> 선언과 동시에 객체 생성)
val obj = object {
    var data = 10
    fun some() {
        println("data : $data")
    }
}
// 클래스를 선언했지만 타입을 명시하지 않았기에 객체가 코틀린의 죄상위 타입인 Any로 취급됨. -> Any 타입 객체에는 data,some()이라는 멤버가 없어서 오류 발생
fun main() {
    obj.data = 20   // 오류
    obj.some()  // 오류
}

// 클래스를 Super 타입으로 선언 (Super 클래스르 상속 받은 하위 클래스를 익명으로 선언) / 클래스가 아니라 인터페이스였다면 그 인터페이스를 구현한 익명 클래스를 선언한 것
open class Super {
    open var data = 10
    open fun some() {
        println("i am super some() : $data")
    }
}
val obj = object: Super() {
    override var data = 20
    override fun some() {
        println("i am object some() : $data")
    }
}
fun main() {
    obj.data = 30  // 성공
    obj.some()  // 성공  "i am object some() : 30" 출력
    }

// 컴패니언 클래스의 멤버 접근 (일반 클래스는 객체를 생성한 후 객체를 통해서 멤버에 접근함 (Class.data : 불가능))
class MyClass {
    companion object {
        var data = 10
        fun some() {
            println(data)
        }
    }
}
fun main() {
    MyClass.data = 20  // 성공
    MyClass.some()  // 성공
}

// 람다 함수 선언과 호출 (람다 함수: 익명 함수 정의 기법)
fun main() {
    val sum = {no1: Int, no2: Int -> no1 + no2}
    sum(10, 20)  // 람다 함수 호출
    /*
    일반 함수 선언과 비교
    fun sum(no1: Int, no2: Int): Int {
        return no1 + no2
    }
    */

    // 람다 함수 선언과 호출 동시에
    {no1: Int, no2: Int -> no1 + no2} (10, 20)

    // 매개변수가 없는 람다 함수
    {-> println("function call")}
    {println("function call")} // 화살표 생략

    // 매개변수가 1개인 람다 함수
    val some = {no: Int -> println(no)}
    some(10)
    // it 키워드 사용
    val some: (Int) -> Unit = {println(it)}  // 람다 함수의 매개변수가 1개일 때는 중괄호 안에 매개변수 선언 생략 가능
    some(10)

    val some = {println(it)}  // 오류 (매개변수 타입을 식별 가능하지 않기 때문)
    val some: (Int) -> Unit = {println(it)}  // 성공 (매개변수 타입을 식별 가능)

    val some = {no1: Int, no2: Int -> return no1 * no2}  // 오류 (람다 함수에서 return문 사용 불가)
    // 람다 함수의 반환값은 본문의 마지막 줄의 실행 결과임
    val some = {no1: Int, no2: Int ->
        println("in lamda function")
        no1 * no2
    }
    println("result : ${some(10, 20)}")  // "in lamda function \nresult : 200" 출력

    // 함수 타입 선언
    val some: (Int, Int) -> Int = { no1: Int, no2: Int -> no1 + no2 }
    /*
    일반 함수 선언
    fun some(no1: Int, no2: Int): Int {
        return no1 + no2
    }
    */

}

// 타입 별칭 선언
typealias MyInt = Int
// 함수 타입 별칭
typealias MyFunType = (Int, Int) -> Boolean
fun main() {
    // 타입 별칭 사용
    val data1: Int = 10
    val data2: MyInt = 10

    // 함수 타입 별칭 사용
    val someFun: MyFunType = {no1: Int, no2: Int ->
        no1 > no2
    }
    println(someFun(10, 20))  // false
    println(someFun(20, 10))  // true
}
// 매개변수 타입을 생략한 함수 선언
typealias MyFunType = (Int, Int) -> Boolean
val someFun: MyFunType = {no1, no2 ->
    no1 > no2
}
// 매개변수 타입 선언 생략 예
val someFun: (Int, Int) -> Boolean = {no1, no2 ->
    no1 > no2
}
// 변수 선언 시 타입 생략
val someFun = { no1: Int, no2: Int ->
    no1 > no2
}

// 고차 함수 (함수를 매개변수로 전달받거나 반환하는 함수)
fun hofFun(arg: (Int) -> Boolean): () -> String {  // 매개변수 타입도 함수, 반환 타입도 함수
    val result = if(arg(10)) {
        "valid"
    } else {
        "invalid"
    }
    return {"hofFun result : $result"}
}
fun main() {
    val result = hofFun({no -> no > 0})
    println(result())   // hofFun result : valid
}

// 널 안정성 (널 포인트 예외(널인 상태의 객체를 이용)가 발생하지 않도록 코드를 작성하는 것)
fun main() {
    var data: String? = null
    val length = if (data == null) {
        0
    } else {
        data.length
    }
    println("data length : $length")  // 0

    // 널 안정선 연산자 이용
    println("data length : ${data?.length ?: 0}")  // 0

    // 널 허용과 널 불허
    var data1: String = "kkang"
    data1 = null // 오류
    var data2: String? = "kkang"
    data2 = null  // 성공

    //널 포인트 예외 오류
    var data3: String? = "kkang"
    var length1 = data3.length  // 오류

    // 널 안정성 호출 연산자(?.) 사용
    var data4: String? = "kkang"
    var length2 = data4?.length  // 성공

    // 엘비스 연산자(?:) 사용
    var data5: String? = "kkang"
    println("data = $data : ${data?.length ?: -1}")  // data = kkang : 5
    data5 = null
    println("data = $data : ${data?.length ?: -1}")  // data = null : -1
}
// 예외 발생 연산자(!!)
fun some(data: String?): Int {
    return data!!.length
}
fun main() {
    println(some("kkang"))  // 5
    println(some(null))  // 오류 메세지
}

class MainActiity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}