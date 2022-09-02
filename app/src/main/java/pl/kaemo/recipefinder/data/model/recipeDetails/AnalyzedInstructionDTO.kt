package pl.kaemo.recipefinder.data.model.recipeDetails

data class AnalyzedInstructionDTO(
    val name: String,
    val steps: List<StepDTO>
)