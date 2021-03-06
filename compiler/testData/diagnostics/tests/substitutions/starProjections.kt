// !WITH_NEW_INFERENCE
// !CHECK_TYPE

interface A<R, T: A<R, T>> {
    fun r(): R
    fun t(): T
}

fun testA(a: A<*, *>) {
    a.r().checkType { _<Any?>() }
    a.t().checkType { _<A<*, *>>() }
}

interface B<R, T: B<List<R>, <!UPPER_BOUND_VIOLATED!>T<!>>> {
    fun r(): R
    fun t(): T
}

fun testB(b: B<*, *>) {
    <!OI;TYPE_MISMATCH!>b<!>.<!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!>r<!>().<!NI;DEBUG_INFO_MISSING_UNRESOLVED!>checkType<!> { <!NI;UNRESOLVED_REFERENCE!>_<!><Any?>() }
    <!OI;TYPE_MISMATCH!>b<!>.<!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!>t<!>().<!NI;DEBUG_INFO_MISSING_UNRESOLVED!>checkType<!> { <!NI;UNRESOLVED_REFERENCE!>_<!><B<List<*>, *>>() }

    <!OI;TYPE_MISMATCH!><!OI;TYPE_MISMATCH!>b<!>.<!NI;UNRESOLVED_REFERENCE_WRONG_RECEIVER!>t<!>()<!>.<!NI;DEBUG_INFO_MISSING_UNRESOLVED!>r<!>().<!NI;DEBUG_INFO_MISSING_UNRESOLVED!>size<!>
}

