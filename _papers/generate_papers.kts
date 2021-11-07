import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.util.concurrent.TimeUnit

val papers = """

title: [AutumnSynth: Synthesis of Reactive Programs with Structured Latent State](https://openreview.net/forum?id=Qw8eyl2_N_-)
authors: [Ria Das](https://www.csail.mit.edu/person/ria-das), [Joshua B. Tenenbaum](http://web.mit.edu/cocosci/josh.html), [Armando Solar-Lezama](https://people.csail.mit.edu/asolar/), [Zenna Tavares](http://www.zenna.org/)

title: [Type Inference as Optimization](https://openreview.net/forum?id=yHYZaQ0Zvml)
authors: [Eirene Vlassi Pandi](http://www.inf.ed.ac.uk/people/students/Eirini_Irene_Vlassi_Pandi.html), [Earl Barr](https://earlbarr.com/), [Andrew D. Gordon](https://www.microsoft.com/en-us/research/people/adg/), [Charles Sutton](https://homepages.inf.ed.ac.uk/csutton/)

title: [Are Transformers All That Karel Needs?](https://openreview.net/forum?id=qGDIkNmWydG)
authors: Abhay Garg, [Anand Sriraman](https://scholar.google.co.in/citations?user=3NjtqroAAAAJ), Kunal Pagarey, [Shirish Karande](https://scholar.google.co.in/citations?user=LeHCh80AAAAJ)

title: [Towards Neural Functional Program Evaluation](https://openreview.net/forum?id=pFy0jbqiCDY)
authors: [Torsten Scholak](https://tscholak.github.io/), [Jonathan Pilault](https://scholar.google.com/citations?user=PNFW8HwAAAAJ), [Joey Velez-Ginorio](https://www.seas.upenn.edu/~joeyv/)

title: <a href="https://openreview.net/forum?id=5TCfWXk2waG">Staged compilation of tensor expressions</a>
authors: <a href="https://github.com/ocramz">Marco Zocca</a>

title: [Safe Neurosymbolic Learning with Differentiable Symbolic Execution](https://openreview.net/forum?id=ZtyvT0aHNBP)
authors: [Chenxi Yang](https://cxyang1997.github.io/), [Swarat Chaudhuri](https://www.cs.utexas.edu/~swarat/)

title: [Learning Rules with Stratified Negation in Differentiable ILP](https://openreview.net/forum?id=BOtQHCVIh_K)
authors: [Giri P Krishnan](https://scholar.google.com/citations?user=IGsdszkAAAAJ), [Frederick Maier](https://scholar.google.com/citations?user=Tj47doAAAAAJ&hl=en), [Ramyaa Ramyaa](https://www.cs.nmt.edu/~ramyaa/)

title: [PAC Synthesis of Machine Learning Programs](https://openreview.net/forum?id=2NskntTea1v)
authors: [Osbert Bastani](https://obastani.github.io/)

title: [Learning compositional programs with arguments and sampling](https://arxiv.org/pdf/2109.00619.pdf)
authors: [Giovanni De Toni](https://detoni.me/), [Luca Erculiani](https://scholar.google.com/citations?user=kzYHFWMAAAAJ), [Andrea Passerini](https://disi.unitn.it/~passerini/)

title: [Learning Adaptive Control Flow in Transformers for Improved Systematic Generalization](https://openreview.net/pdf?id=v8IbnUesFpE)
authors: [Róbert Csordás](https://robertcsordas.github.io/), [Kazuki Irie](https://www-i6.informatik.rwth-aachen.de/~irie/), [Jürgen Schmidhuber](https://people.idsia.ch/~juergen/)

title: [AutoCoder: Leveraging Transformers for Automatic Code Synthesis](https://openreview.net/forum?id=fIU0j2MXTIa)
authors: [Mrinal Anand](https://kaiyon07.github.io/), [Pratik Kayal](https://scholar.google.com/citations?user=WOrOyp4AAAAJ), [Mayank Singh](https://mayank4490.github.io/)

title: [Adversarial Robustness of Program Synthesis Models](https://openreview.net/forum?id=17C-dfA5X69)
authors: [Mrinal Anand](https://kaiyon07.github.io/), [Pratik Kayal](https://scholar.google.com/citations?user=WOrOyp4AAAAJ), [Mayank Singh](https://mayank4490.github.io/)

title: [Learning C to x86 Translation: An Experiment in Neural Compilation](https://arxiv.org/pdf/2108.07639.pdf) / [code](https://github.com/jordiae/neural-compilers)
authors: [Jordi Armengol-Estapé](https://scholar.google.es/citations?user=CiHoJfcAAAAJ), [Michael O'Boyle](http://www.dcs.ed.ac.uk/home/mob/)

title: [Synthesizing Video Trajectory Queries](https://openreview.net/forum?id=HyTIeooyV2H)
authors: Stephen Mell, [Favyen Bastani](https://favyen.com/), [Stephan Zdancewic](https://www.cis.upenn.edu/~stevez/), [Osbert Bastani](https://obastani.github.io/)

title: [Augmenting Classic Algorithms with Neural Components for Strong Generalisation on Ambiguous and High-Dimensional Data](https://openreview.net/forum?id=_Y4FQu1aJ1Z)
authors: [Imanol Schlag](https://ischlag.github.io/about/), [Jürgen Schmidhuber](https://people.idsia.ch/~juergen/)

title: [Meta-Learning an Inference Algorithm for Probabilistic Programs](https://arxiv.org/pdf/2103.00737.pdf)
authors: [Gwonsoo Che](https://sites.google.com/view/gwonsoo-che), [Hongseok Yang](https://sites.google.com/view/hongseokyang/home)

title: [Scallop: From Probabilistic Deductive Databases to Scalable Differentiable Reasoning](https://openreview.net/forum?id=qey0t9ivuBv) / [code](https://github.com/scallop-lang/scallop-v1)
authors: [Jiani Huang](https://www.cis.upenn.edu/~jianih/), [Ziyang Li](https://liby99.github.io/), [Binghong Chen](http://binghongchen.net/), [Karan Samel](https://karans.github.io/), [Mayur Naik](https://www.cis.upenn.edu/~mhnaik/), [Le Song](https://scholar.google.com/citations?user=Xl4E0CsAAAAJ), [Xujie Si](https://www.cs.mcgill.ca/~xsi/)

title: [LazyPPL: laziness and types in non-parametric probabilistic programs](https://openreview.net/forum?id=yHox9OyegeX)
authors: [Hugo Paquet](https://www.cs.ox.ac.uk/people/hugo.paquet/main.html), [Sam Staton](http://www.cs.ox.ac.uk/people/samuel.staton/main.html)

title: [Proof Extraction for Logical Neural Networks](https://openreview.net/forum?id=Xw3kb6UyA31)
authors: [Thabang Lebese](https://scholar.google.com/citations?user=0mF32KgAAAAJ), [Ndivhuwo Makondo](https://researcher.watson.ibm.com/researcher/view.php?person=ibm-Ndivhuwo.Makondo), [Cristina Cornelio](https://scholar.google.com/citations?user=EP9lmrcAAAAJ), [Naweed Khan](https://scholar.google.com/citations?user=CXaTRZUAAAAJ)

title: [A Genetic Programming Approach To Zero-Shot Neural Architecture Ranking](https://openreview.net/forum?id=xuVVuLcqBP5)
authors: [Yash Akhauri](https://akhauriyash.github.io/), [Juan Pablo Munoz](https://www.intel.ca/content/www/ca/en/research/researchers/j--pablo-munoz.html), [Ravishankar Iyer](https://scholar.google.com/citations?user=2rO3ZvEAAAAJ), [Nilesh Jain](https://scholar.google.com/citations?user=sWUGELEAAAAJ)
""".trimIndent()

papers.split(Regex("\n\n")).forEachIndexed { i, lines ->
  val leftPad = i.toString().padStart(2, '0')
  File("paper_$leftPad.md").writeText(
"""
---
sequence_id: $i
$lines
---

""".trimIndent()
  )
}