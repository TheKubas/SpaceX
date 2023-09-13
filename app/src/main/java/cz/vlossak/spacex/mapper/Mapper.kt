package cz.vlossak.spacex.mapper

interface Mapper<I, O> {
    fun map(from: I): O
}