import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.util.concurrent.TimeUnit

val exceptionalReviewers = listOf(
    "Joey Velez-Ginorio"           ,
    "Talia Ringer"                 ,
    "Alex Sanchez-Stern"           ,
    "Boyan Beronov"                ,
    "Chenxi Yang"                  ,
    "Dan Zheng"                    ,
    "Daniel Johnson"               ,
    "Hugo Paquet"                  ,
    "Izzeddin Gur"                 ,
    "Krishna Murthy Jatavallabhula",
    "Jacob Austin"                 ,
    "Jordi Armengol-Estapé"        ,
    "Kia Rahmani"                  ,
    "Kiran Gopinathan"             ,
    "Marco Zocca"                  ,
    "Pascal Lamblin"               ,
    "Rif Saurous"                  ,
    "Robin Manhaeve"               ,
    "Stefan Monnier"               ,
    "Uri Alon"                     ,
    "Yash Akhauri"                 ,
)

val latex = File("pdfs/reviewer_appreciation.tex").readText()

exceptionalReviewers.forEach { reviewer ->
  val reviewerLatex = latex.replace("@reviewer@", reviewer)
  val fileName = reviewer.replace(" ", "_").lowercase()
  File("pdfs/$fileName.tex").writeText(reviewerLatex)
  "pdflatex -file-line-error -interaction=nonstopmode -synctex=1 -output-format=pdf $fileName.tex".runCommand()
  "rm $fileName.tex $fileName.aux $fileName.log $fileName.synctex.gz".runCommand()
}

fun String.runCommand(workingDir: File = File("pdfs")) {
    val process = ProcessBuilder(*split(" ").toTypedArray())
        .directory(workingDir)
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
    if (!process.waitFor(30, TimeUnit.SECONDS)) {
        process.destroy()
        throw RuntimeException("execution timed out: $this")
    }
}