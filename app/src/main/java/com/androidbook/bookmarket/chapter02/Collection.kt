package com.androidbook.bookmarket.chapter02

internal fun collectionExamples(output: (String) -> Unit) {
    output("[컬렉션]")
    val lists = listOf("월요일", "화요일", "수요일", "목요일", "금요일")
    val sets = setOf(1, 2, 2, 3, 3, 3, "Java", "Java", "Kotlin")
    val maps = mapOf(1 to "Java", 2 to "Kotlin", 3 to "Android")
    output("불변 리스트: $lists")
    output("첫 번째 요일: ${lists[0]}")
    output("불변 셋: $sets")
    output("셋에 'Java' 포함 여부: ${"Java" in sets}")
    output("불변 맵: $maps")
    output("맵에서 key 2에 해당하는 값: ${maps[2]}")
    val mutableLists = mutableListOf("월요일", "화요일", "수요일", "목요일", "금요일")
    val mutableSets = mutableSetOf(6, 10)
    val mutableMaps = mutableMapOf(1 to "Java", 2 to "Kotlin", 3 to "Android")
    output("초기 리스트: $mutableLists")
    output("초기 셋: $mutableSets")
    output("초기 맵: $mutableMaps")
    mutableLists[0] = "일요일"
    mutableLists.add("토요일")
    mutableSets.add(2)
    mutableSets.add(5)
    mutableMaps[1] = "C#"
    mutableMaps[4] = "C++"
    output("변경된 리스트: $mutableLists")
    output("변경된 셋: $mutableSets")
    output("변경된 맵: $mutableMaps")
}
