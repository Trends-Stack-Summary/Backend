package com.project.batch.constants

enum class CollectionType {
    RSS,
    HTML,               // HTML 수집은 해당 기업에서 구조가 조금이라도 변경될 우려가 있어서 일체 수집하지않고 하위 호환으로 놔둡니다.
    PLAYWRIGHT,         // PLAYWRIGHT 수집은 해당 기업에서 구조가 조금이라도 변경될 우려가 있어서 일체 수집하지않고 하위 호환으로 놔둡니다.
}