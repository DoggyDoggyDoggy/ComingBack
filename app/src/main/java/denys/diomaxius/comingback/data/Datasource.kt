package denys.diomaxius.comingback.data

import denys.diomaxius.comingback.R
import denys.diomaxius.comingback.model.Guide


enum class GuideChapters(val displayName: String) {
    BasicLogic("BASIC LOGIC"),
    Arithmetic("ARITHMETIC"),
    Memory("MEMORY"),
    CpuArchitecture("CPU ARCHITECTURE")
}

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

    val arithmeticGuides = listOf(
        Guide("Double Trouble", R.drawable.double_trouble, R.string.double_trouble),
        Guide("ODD Number of Signals", R.drawable.odd_number_of_signals, R.string.odd_number_of_signals),
        Guide("Counting Signals", R.drawable.counting_signals, R.string.counting_signals),
        Guide("Half Adder", R.drawable.half_adder, R.string.half_adder),
        Guide("Double the Number", R.drawable.double_trouble, R.string.double_the_number),
        Guide("Full Adder", R.drawable.full_adder, R.string.full_adder),
        Guide("Byte OR", R.drawable.byte_or, R.string.byte_or),
        Guide("Byte NOT", R.drawable.byte_not, R.string.byte_not),
        Guide("Adding Bytes", R.drawable.adding_bytes, R.string.adding_bytes),
        Guide("Signed negator", R.drawable.signed_negator, R.string.signed_negator),
        Guide("1 bit decoder", R.drawable.one_bit_decoder, R.string.one_bit_decoder),
        Guide("3 bit decoder", R.drawable.three_bit_decoder, R.string.three_bit_decoder),
        Guide("Logic Engine", R.drawable.logic_engine, R.string.logic_engine)
    )
}