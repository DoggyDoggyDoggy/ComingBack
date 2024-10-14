package denys.diomaxius.comingback.data

import denys.diomaxius.comingback.R
import denys.diomaxius.comingback.model.Guide

val NUMBER_OF_GUIDES = Datasource.basicLogicGuides.size

object Datasource {
    val basicLogicGuides = listOf(
        Guide("NOT Gate", R.drawable.not_gate, R.string.not_gate),
        Guide("AND Gate", R.drawable.and_gate, R.string.and_gate),
        Guide("NOR Gate", R.drawable.nor_gate, R.string.nor_gate),
        Guide("OR Gate", R.drawable.or_gate, R.string.or_gate),
        Guide("Always ON Gate", R.drawable.always_gate, R.string.always_on_gate),
        Guide("Second Tick", R.drawable.second_tick, R.string.second_tick_gate),
        Guide("XOR Gate", R.drawable.xor_gate, R.string.xor_gate)
    )
}