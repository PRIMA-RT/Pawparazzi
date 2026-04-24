package com.example.pawparazzi.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light Scheme
val PrimaryLight = Color(0xFF904A4B)
val OnPrimaryLight = Color(0xFFFFFFFF)
val PrimaryContainerLight = Color(0xFFFFDAD9)
val OnPrimaryContainerLight = Color(0xFF733335)

val SecondaryLight = Color(0xFF08677F)
val OnSecondaryLight = Color(0xFFFFFFFF)
val SecondaryContainerLight = Color(0xFFB8EAFF)
val OnSecondaryContainerLight = Color(0xFF004D61)

val TertiaryLight = Color(0xFF755B0B)
val OnTertiaryLight = Color(0xFFFFFFFF)
val TertiaryContainerLight = Color(0xFFFFDF97)
val OnTertiaryContainerLight = Color(0xFF5A4400)

val ErrorLight = Color(0xFFBA1A1A)
val OnErrorLight = Color(0xFFFFFFFF)
val ErrorContainerLight = Color(0xFFFFDAD6)
val OnErrorContainerLight = Color(0xFF93000A)

val SurfaceLight = Color(0xFFFFF8F7)
val OnSurfaceLight = Color(0xFF221919)
val SurfaceVariantLight = Color(0xFFF0DEDE)
val OnSurfaceVariantLight = Color(0xFF524343)
val OutlineLight = Color(0xFF857372)
val OutlineVariantLight = Color(0xFFD7C1C1)

// Dark Scheme (Inferred from Fixed Dim and Inverse colors in Figma)
val PrimaryDark = Color(0xFFFFB3B2)
val OnPrimaryDark = Color(0xFF561D1F)
val PrimaryContainerDark = Color(0xFF733335)
val OnPrimaryContainerDark = Color(0xFFFFDAD9)

val SecondaryDark = Color(0xFF88D1EC)
val OnSecondaryDark = Color(0xFF003541)
val SecondaryContainerDark = Color(0xFF004D61)
val OnSecondaryContainerDark = Color(0xFFB8EAFF)

val TertiaryDark = Color(0xFFE6C26C)
val OnTertiaryDark = Color(0xFF3E2E00)
val TertiaryContainerDark = Color(0xFF5A4400)
val OnTertiaryContainerDark = Color(0xFFFFDF97)

val ErrorDark = Color(0xFFFFB4AB)
val OnErrorDark = Color(0xFF690005)
val ErrorContainerDark = Color(0xFF93000A)
val OnErrorContainerDark = Color(0xFFFFDAD6)

val SurfaceDark = Color(0xFF1A1111)
val OnSurfaceDark = Color(0xFFF0DEDE)
val SurfaceVariantDark = Color(0xFF524343)
val OnSurfaceVariantDark = Color(0xFFD7C1C1)
val OutlineDark = Color(0xFF9F8C8C)
val OutlineVariantDark = Color(0xFF524343)

val BackgroundColor @Composable get() = MaterialTheme.colorScheme.background
val SurfaceContainerHighDark @Composable get() = MaterialTheme.colorScheme.surfaceContainerHigh


val PastelRed = Color(0xFFFFADAD)
val PastelOrange = Color(0xFFFFD6A5)
val PastelYellow = Color(0xFFFDFFB6)
val PastelGreen = Color(0xFFCAFFBF)
val PastelBlue = Color(0xFF9BF6FF)
val PastelTeal = Color(0xFF98FFEE)
val PastelMauve = Color(0xFFA0C4FF)
val PastelPurple = Color(0xFFBDB2FF)
val PastelPink = Color(0xFFFFC6FF)

val pastelColors = listOf(
    PastelRed,
    PastelOrange,
    PastelYellow,
    PastelGreen,
    PastelBlue,
    PastelTeal,
    PastelMauve,
    PastelPurple,
    PastelPink
)