import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String fistName = "";
    private String secondName = "";
    private String familyName = "";
    private String phone = "";
    private String dateOfBirth = "";
    private String gender = "";

    public String getFamilyName(boolean i) {
        if (i){
            return familyName;
        } else {
            return "?" + familyName;
        }

    }

    public void getStringToPerson(String input) throws InvalidInputExeption {
        String strPatternFIO = "(^|\\s)[А-ЯA-Z][а-яa-z]*\\s[А-ЯA-Z][а-яa-z]*\\s[А-ЯA-Z][а-яa-z]*($|\\s)";
        String strPatternTel = "(^|\\s)\\d{11}($|\\s)";
        String strPatternBorn = "(^|\\s)\\d{2}.\\d{2}.\\d{4}($|\\s)";
        String strPatternGender = "(^|\\s)[fm]($|\\s)";
        String errText = "";

        Pattern patternFIO = Pattern.compile(strPatternFIO);
        Pattern patternTel = Pattern.compile(strPatternTel);
        Pattern patternBorn = Pattern.compile(strPatternBorn);
        Pattern patternGender = Pattern.compile(strPatternGender);

        Matcher matcherFIO = patternFIO.matcher(input);
        Matcher matcherTel = patternTel.matcher(input);
        Matcher matcherBorn = patternBorn.matcher(input);
        Matcher matcherGender = patternGender.matcher(input);

        if (matcherFIO.find()) {
            String[] sFIO = matcherFIO.group(0).trim().split(" ");
            familyName = sFIO[0];
            fistName = sFIO[1];
            secondName = sFIO[2];
            if (matcherFIO.find(matcherFIO.start() + familyName.length() - 1)) {
                errText += "В введённой строке больше одной подстроки, подходящей под ФИО, допустимо только Фамилия " +
                        "Имя Отчество\n";
            }
        } else {
            errText += "В введённой строке нет ФИО или введено неправильно, допустимо только Фамилия Имя Отчество\n";
        }

        if (matcherTel.find()) {
            phone = matcherTel.group(0).trim();
            if (matcherTel.find(matcherTel.end() - 1)) {
                errText += "В введённой строке больше одного упоминания номера телефона\n";
            }
        } else {
            errText += "В введённой строке нет номера телефона или введено неправильно, допустимо только 11 цифр в " +
                    "номере\n";
        }

        if (matcherBorn.find()) {
            dateOfBirth = matcherBorn.group(0).trim();
            if (matcherBorn.find(matcherBorn.end() - 1)) {
                errText += "В введённой строке больше одного упоминания даты рождения\n";
            }
        } else {
            errText += "В введённой строке нет даты рождения или введено неправильно, допустимо только dd.mm.yyyy\n";
        }

        if (matcherGender.find()) {
            gender = matcherGender.group(0).trim();
            if (matcherGender.find(matcherGender.end() - 1)) {
                errText += "В введённой строке больше одного упоминания пола\n";
            }
        } else {
            errText += "В введённой строке нет подходящего пола или введено неправильно, допустимо только f или m\n";
        }
        if (input.isEmpty()) {
            errText = "Входящие данные пусты\n";
        }
        if (!errText.isEmpty()){
            throw new InvalidInputExeption(errText + "Файл не создается");
        }

    }

    public String getAll(){
        return familyName + " " + fistName + " " + secondName + " " + dateOfBirth + " " + phone + " " + gender + "\n";
    }

    @Override
    public String toString() {
        return "Person{" +
                "fistName='" + fistName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
