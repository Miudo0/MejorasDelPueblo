package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.empresa.aplicacion.R


val fontprovider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs

    )

val custombodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Cormorant Garamond"),
        fontProvider = fontprovider,
    )
)

val customdisplayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("EB Garamond"),
        fontProvider = fontprovider,
    )
)

// Default Material 3 typography values
val custombaseline = Typography()

val customAppTypography = Typography(
    displayLarge = custombaseline.displayLarge.copy(fontFamily = customdisplayFontFamily),
    displayMedium = custombaseline.displayMedium.copy(fontFamily = customdisplayFontFamily),
    displaySmall = custombaseline.displaySmall.copy(fontFamily = customdisplayFontFamily),
    headlineLarge = custombaseline.headlineLarge.copy(fontFamily = customdisplayFontFamily),
    headlineMedium = custombaseline.headlineMedium.copy(fontFamily = customdisplayFontFamily),
    headlineSmall = custombaseline.headlineSmall.copy(fontFamily = customdisplayFontFamily),
    titleLarge = custombaseline.titleLarge.copy(fontFamily = customdisplayFontFamily),
    titleMedium = custombaseline.titleMedium.copy(fontFamily = customdisplayFontFamily),
    titleSmall = custombaseline.titleSmall.copy(fontFamily = customdisplayFontFamily),
    bodyLarge = custombaseline.bodyLarge.copy(fontFamily = custombodyFontFamily),
    bodyMedium = custombaseline.bodyMedium.copy(fontFamily = custombodyFontFamily),
    bodySmall = custombaseline.bodySmall.copy(fontFamily = custombodyFontFamily),
    labelLarge = custombaseline.labelLarge.copy(fontFamily = custombodyFontFamily),
    labelMedium = custombaseline.labelMedium.copy(fontFamily = custombodyFontFamily),
    labelSmall = custombaseline.labelSmall.copy(fontFamily = custombodyFontFamily),
)

