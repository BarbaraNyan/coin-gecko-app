package com.example.trainee_test.data.dto.description

import com.example.trainee_test.model.CryptoDescription

class CoinDescriptionDTO(
    val id: String,
    val name: String,
    val image: Image,
    val description: Description,
    val categories: List<String>
)
{
    fun toCryptoDescription(): CryptoDescription{
        return CryptoDescription(name = name, image = image.large,
            description = description.en, categories = categories)
    }
}