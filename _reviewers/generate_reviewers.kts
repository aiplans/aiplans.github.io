import java.io.File

val reviewers = """
name: [Alex Sanchez-Stern](http://alex.uwplse.org/)
img: alex.png

name: [Boyan Beronov](https://scholar.google.com/citations?user=mJH2wncAAAAJ&hl=en)
img: boyan.png

name: [Dan Zheng](https://danzheng.me/)
img: dan.png

name: [Daniel D Johnson](https://www.danieldjohnson.com/)
img: daniel.png

name: [Izzeddin Gür](https://scholar.google.com/citations?user=qS_ugJAAAAAJ&hl=en)
img: izzeddin.png

name: [Jacob Austin](http://www.jacobaustin.org/)
img: jacob.png

name: [Kia Rahmani](https://kiarahmani.github.io/)
img: kia.png

name: [Pascal Lamblin](https://research.google/people/106861/)
img: pascal.png

name: [Robin Manhaeve](https://scholar.google.be/citations?user=pQht1BIAAAAJ)
img: robin.png

name: [Stefan Monnier](https://www.iro.umontreal.ca/~monnier/)
img: stefan.png

name: [Uri Alon](https://urialon.ml/)
img: uri.png

name: [Joey Velez-Ginorio](https://www.seas.upenn.edu/~joeyv/)
img: joey.png

name: [Talia Ringer](https://dependenttyp.es/)
img: talia.png

name: [Hugo Paquet](https://www.cs.ox.ac.uk/people/hugo.paquet/main.html)
img: hugo.png

name: [Krishna Murthy Jatavallabhula](https://krrish94.github.io/)
img: krishna.png

name: [Jordi Armengol-Estapé](https://scholar.google.es/citations?user=CiHoJfcAAAAJ&hl=en)
img: jordi.png

name: [Rif A. Saurous](https://derifatives.github.io/about/)
img: rif.png

name: [Abhay Garg](https://www.linkedin.com/in/abhay-garg-04a582137)
img: abhay.png

name: [Alcides Fonseca](https://utaustinportugal.org/participants/alcides-fonseca/)
img: alcides.png

name: [Chenxi Yang](https://cxyang1997.github.io/)
img: chenxi.png

name: [Giovanni De Toni](https://detoni.me/)
img: giovanni.png

name: [Giri P Krishnan](https://profiles.ucsd.edu/giri.krishnan)
img: giri.png

name: [Kiran Gopinathan](https://gopiandcode.uk/)
img: kiran.png

name: [Marco Zocca](https://conf.researchr.org/profile/conf/marcozocca1)
img: marco.png

name: [Mrinal Anand](https://kaiyon07.github.io/)
img: mrinal.png

name: [Prakash Panangaden](https://www.cs.mcgill.ca/~prakash/)
img: prakash.png

name: [Shagun Sodhani](https://shagunsodhani.com/)
img: shagun.png

name: [Stephen Mell](https://www.cis.upenn.edu/~sm1/)
img: stephen.png

name: [Yash Akhauri](https://scholar.google.com/citations?user=6rbWWzEAAAAJ&hl=en)
img: yash.png

name: [Charanraj Thimmisetty](https://scholar.google.com/citations?user=bnnlwwIAAAAJ)
img: charanraj.png

name: [Ohad Kammar](http://denotational.co.uk/)
img: ohad.png
""".trimIndent()

reviewers.split(Regex("\n\n")).forEachIndexed { i, lines ->
  val leftPad = i.toString().padStart(2, '0')
  File("reviewer_$leftPad.md").writeText(
    """
---
sequence_id: $i
${lines.translateMarkdownToHTML()}
---

""".trimIndent()
  )
}

fun String.translateMarkdownToHTML() =
  Regex("\\[(.*?)\\]\\((.*?)\\)").replace(this){ mr ->
    val (text, link) = mr.destructured
    "<a href=\"$link\">${text.replace(":", "&#58;")}</a>"
  }