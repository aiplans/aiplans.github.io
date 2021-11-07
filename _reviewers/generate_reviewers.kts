import java.io.File

val reviewers = """
name: [Alex Sanchez-Stern](http://alex.uwplse.org/)
img: alex.jpg

name: [Boyan Beronov](https://scholar.google.com/citations?user=mJH2wncAAAAJ&hl=en)
img: boyan.jpg

name: [Dan Zheng](https://danzheng.me/)
img: dan.jpg

name: [Daniel D Johnson](https://www.danieldjohnson.com/)
img: daniel.jpg

name: [Izzeddin Gür](https://scholar.google.com/citations?user=qS_ugJAAAAAJ&hl=en)
img: avatar.jpg

name: [Jacob Austin](http://www.jacobaustin.org/)
img: jacob.jpg

name: [Kia Rahmani](https://kiarahmani.github.io/)
img: kia.jpg

name: [Pascal Lamblin](https://research.google/people/106861/)
img: pascal.jpg

name: [Robin Manhaeve](https://scholar.google.be/citations?user=pQht1BIAAAAJ)
img: robin.jpg

name: [Stefan Monnier](https://www.iro.umontreal.ca/~monnier/)
img: stefan.jpg

name: [Uri Alon](https://urialon.ml/)
img: uri.jpg

name: [Joey Velez-Ginorio](https://www.seas.upenn.edu/~joeyv/)
img: joey.jpg

name: [Talia Ringer](https://dependenttyp.es/)
img: talia.jpg

name: [Hugo Paquet](https://www.cs.ox.ac.uk/people/hugo.paquet/main.html)
img: hugo.jpg

name: [Krishna Murthy Jatavallabhula](https://krrish94.github.io/)
img: krishna.jpg

name: [Jordi Armengol-Estapé](https://scholar.google.es/citations?user=CiHoJfcAAAAJ&hl=en)
img: jordi.jpg

name: [Rif A. Saurous](https://derifatives.github.io/about/)
img: rif.jpg

name: [Abhay Garg](https://www.linkedin.com/in/abhay-garg-04a582137)
img: avatar.jpg

name: [Alcides Fonseca](https://utaustinportugal.org/participants/alcides-fonseca/)
img: alcides.jpg

name: [Chenxi Yang](https://cxyang1997.github.io/)
img: chenxi.jpg

name: [Giovanni De Toni](https://detoni.me/)
img: giovanni.jpg

name: [Giri P Krishnan](https://profiles.ucsd.edu/giri.krishnan)
img: giri.jpg

name: [Kiran Gopinathan](https://gopiandcode.uk/)
img: kiran.jpg

name: [Marco Zocca](https://conf.researchr.org/profile/conf/marcozocca1)
img: marco.jpg

name: [Mrinal Anand](https://kaiyon07.github.io/)
img: mrinal.jpg

name: [Prakash Panangaden](https://www.cs.mcgill.ca/~prakash/)
img: prakash.jpg

name: [Shagun Sodhani](https://shagunsodhani.com/)
img: shagun.jpg

name: [Stephen Mell](https://www.cis.upenn.edu/~sm1/)
img: stephen.jpg

name: [Yash Akhauri](https://scholar.google.com/citations?user=6rbWWzEAAAAJ&hl=en)
img: yash.jpg

name: [Charanraj Thimmisetty](https://scholar.google.com/citations?user=bnnlwwIAAAAJ)
img: charanraj.jpg

name: [Ohad Kammar](http://denotational.co.uk/)
img: ohad.jpg
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