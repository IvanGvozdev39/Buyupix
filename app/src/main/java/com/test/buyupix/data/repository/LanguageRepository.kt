import com.test.buyupix.domain.model.Language
import com.test.buyupix.R

enum class LanguageRepository(val code: String, private val flagIconRes: Int, private val phoneCode: String, private val maxLengthNoCode: Int) {
    BY("BY", R.drawable.ic_flag_by, "+375", 14),
    RU("RU", R.drawable.ic_flag_ru, "+7", 15),
    US("US", R.drawable.ic_flag_us, "+1", 15);

    companion object {
        fun getLanguageByCode(code: String): LanguageRepository? {
            return entries.find { it.code == code }
        }
    }

    fun toDomainModel(): Language {
        return Language(code, flagIconRes, phoneCode, maxLengthNoCode)
    }
}