package `in`.bpj4.pokedex.util

import androidx.compose.ui.graphics.Color
import `in`.bpj4.pokedex.ui.theme.AtkColor
import `in`.bpj4.pokedex.ui.theme.DefColor
import `in`.bpj4.pokedex.ui.theme.HPColor
import `in`.bpj4.pokedex.ui.theme.SpAtkColor
import `in`.bpj4.pokedex.ui.theme.SpDefColor
import `in`.bpj4.pokedex.ui.theme.SpdColor
import `in`.bpj4.pokedex.ui.theme.TypeBug
import `in`.bpj4.pokedex.ui.theme.TypeDark
import `in`.bpj4.pokedex.ui.theme.TypeDragon
import `in`.bpj4.pokedex.ui.theme.TypeElectric
import `in`.bpj4.pokedex.ui.theme.TypeFairy
import `in`.bpj4.pokedex.ui.theme.TypeFighting
import `in`.bpj4.pokedex.ui.theme.TypeFire
import `in`.bpj4.pokedex.ui.theme.TypeFlying
import `in`.bpj4.pokedex.ui.theme.TypeGhost
import `in`.bpj4.pokedex.ui.theme.TypeGrass
import `in`.bpj4.pokedex.ui.theme.TypeGround
import `in`.bpj4.pokedex.ui.theme.TypeIce
import `in`.bpj4.pokedex.ui.theme.TypeNormal
import `in`.bpj4.pokedex.ui.theme.TypePoison
import `in`.bpj4.pokedex.ui.theme.TypePsychic
import `in`.bpj4.pokedex.ui.theme.TypeRock
import `in`.bpj4.pokedex.ui.theme.TypeSteel
import `in`.bpj4.pokedex.ui.theme.TypeWater
import `in`.bpj4.pokedex.data.remote.responses.Stat
import `in`.bpj4.pokedex.data.remote.responses.Type
import java.util.Locale

fun parseTypeToColor(type: Type): Color {
    return when (type.type.name.toLowerCase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when (stat.stat.name.toLowerCase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when (stat.stat.name.toLowerCase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}