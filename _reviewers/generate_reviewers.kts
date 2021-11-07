import java.io.File

val reviewers = """
name: [Alex Sanchez-Stern](http://alex.uwplse.org/)
img: alex.jpg
affil: UMass Amherst

name: [Boyan Beronov](https://scholar.google.com/citations?user=mJH2wncAAAAJ&hl=en)
img: boyan.jpg
affil: University of British Columbia

name: [Dan Zheng](https://danzheng.me/)
img: dan.jpg
affil: Google

name: [Daniel D Johnson](https://www.danieldjohnson.com/)
img: daniel.jpg
affil: Google

name: [Izzeddin Gür](https://scholar.google.com/citations?user=qS_ugJAAAAAJ&hl=en)
img: avatar.jpg
affil: Google

name: [Jacob Austin](http://www.jacobaustin.org/)
img: jacob.jpg
affil: Google

name: [Kia Rahmani](https://kiarahmani.github.io/)
img: kia.jpg
affil: Purdue University

name: [Pascal Lamblin](https://research.google/people/106861/)
img: pascal.jpg
affil: Google

name: [Robin Manhaeve](https://scholar.google.be/citations?user=pQht1BIAAAAJ)
img: robin.jpg
affil: KU Leuven

name: [Stefan Monnier](https://www.iro.umontreal.ca/~monnier/)
img: stefan.jpg
affil: University of Montreal

name: [Uri Alon](https://urialon.ml/)
img: uri.jpg
affil: Carnegie Mellon University

name: [Joey Velez-Ginorio](https://www.seas.upenn.edu/~joeyv/)
img: joey.jpg
affil: University of Pennsylvania

name: [Talia Ringer](https://dependenttyp.es/)
img: talia.jpg
affil: University of Illinois Urbana-Champaign

name: [Hugo Paquet](https://www.cs.ox.ac.uk/people/hugo.paquet/main.html)
img: hugo.jpg
affil: Oxford

name: [Krishna Murthy Jatavallabhula](https://krrish94.github.io/)
img: krishna.jpg
affil: Mila, IQIA

name: [Jordi Armengol-Estapé](https://scholar.google.es/citations?user=CiHoJfcAAAAJ&hl=en)
img: jordi.jpg
affil: University of Edinburgh

name: [Rif A. Saurous](https://derifatives.github.io/about/)
img: rif.jpg
affil: Google

name: [Abhay Garg](https://www.linkedin.com/in/abhay-garg-04a582137)
img: avatar.jpg
affil: Tata Consultancy Services

name: [Alcides Fonseca](https://utaustinportugal.org/participants/alcides-fonseca/)
img: alcides.jpg
affil: University of Lisbon

name: [Chenxi Yang](https://cxyang1997.github.io/)
img: chenxi.jpg
affil: University of Texas at Austin

name: [Giovanni De Toni](https://detoni.me/)
img: giovanni.jpg
affil: University of Trento

name: [Giri P Krishnan](https://profiles.ucsd.edu/giri.krishnan)
img: giri.jpg
affil: University of California San Diego

name: [Kiran Gopinathan](https://gopiandcode.uk/)
img: kiran.jpg
affil: National University of Singapore

name: [Marco Zocca](https://github.com/ocramz)
img: marco.jpg
affil: UnfoldML

name: [Mrinal Anand](https://kaiyon07.github.io/)
img: mrinal.jpg
affil: IIT Gandhinagar

name: [Prakash Panangaden](https://www.cs.mcgill.ca/~prakash/)
img: prakash.jpg
affil: McGill University

name: [Shagun Sodhani](https://shagunsodhani.com/)
img: shagun.jpg
affil: Mila, IQIA

name: [Stephen Mell](https://www.cis.upenn.edu/~sm1/)
img: stephen.jpg
affil: University of Pennsylvania

name: [Yash Akhauri](https://scholar.google.com/citations?user=6rbWWzEAAAAJ&hl=en)
img: yash.jpg
affil: Intel Labs

name: [Charanraj Thimmisetty](https://scholar.google.com/citations?user=bnnlwwIAAAAJ)
img: charanraj.jpg
affil: Palo Alto Networks

name: [Ohad Kammar](http://denotational.co.uk/)
img: ohad.jpg
affil: University of Edinburgh
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