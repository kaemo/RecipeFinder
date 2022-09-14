package pl.kaemo.recipefinder.data.model.recipeDetails

data class StepDTO(
    val equipment: List<EquipmentDTO>,
    val ingredients: List<IngredientDTO>,
    val length: LengthDTO,
    val number: Int,
    val step: String
)