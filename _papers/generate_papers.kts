import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.util.concurrent.TimeUnit

val papers = """
title: 💡[Meta-Learning an Inference Algorithm for Probabilistic Programs](https://openreview.net/forum?id=--P3fHFWJeF)  
authors: [Gwonsoo Che](https://sites.google.com/view/gwonsoo-che), [Hongseok Yang](https://sites.google.com/view/hongseokyang/home)  
img: metalearn_pp.png  

title: 💡[Learning Rules with Stratified Negation in Differentiable ILP](https://openreview.net/forum?id=BOtQHCVIh_K)
authors: [Giri P Krishnan](https://scholar.google.com/citations?user=IGsdszkAAAAJ), [Frederick Maier](https://www.twinearth.net/), [Ramyaa Ramyaa](https://www.cs.nmt.edu/~ramyaa/)
img: diff_ilp.png

title: 💡[Type Inference as Optimization](https://openreview.net/forum?id=yHYZaQ0Zvml)
authors: [Eirene Vlassi Pandi](http://www.inf.ed.ac.uk/people/students/Eirini_Irene_Vlassi_Pandi.html), [Earl Barr](https://earlbarr.com/), [Andrew D. Gordon](https://www.microsoft.com/en-us/research/people/adg/), [Charles Sutton](https://homepages.inf.ed.ac.uk/csutton/)
img: type_opt.png

title: 💡[LazyPPL: laziness and types in non-parametric probabilistic programs](https://openreview.net/forum?id=yHox9OyegeX)
authors: [Hugo Paquet](https://www.cs.ox.ac.uk/people/hugo.paquet/main.html), [Sam Staton](http://www.cs.ox.ac.uk/people/samuel.staton/main.html)
img: lazy_ppl.png

title: [PAC Synthesis of Machine Learning Programs](https://openreview.net/forum?id=2NskntTea1v)
authors: [Osbert Bastani](https://obastani.github.io/)
img: pac_synth.png

title: [Scallop: From Probabilistic Deductive Databases to Scalable Differentiable Reasoning](https://openreview.net/forum?id=qey0t9ivuBv) / [code](https://github.com/scallop-lang/scallop-v1)
authors: [Jiani Huang](https://www.cis.upenn.edu/~jianih/), [Ziyang Li](https://liby99.github.io/), [Binghong Chen](http://binghongchen.net/), [Karan Samel](https://karans.github.io/), [Mayur Naik](https://www.cis.upenn.edu/~mhnaik/), [Le Song](https://scholar.google.com/citations?user=Xl4E0CsAAAAJ), [Xujie Si](https://www.cs.mcgill.ca/~xsi/)
img: scallop.png

title: 💡[Learning Adaptive Control Flow in Transformers for Improved Systematic Generalization](https://openreview.net/pdf?id=v8IbnUesFpE)
authors: [Róbert Csordás](https://robertcsordas.github.io/), [Kazuki Irie](https://www-i6.informatik.rwth-aachen.de/~irie/), [Jürgen Schmidhuber](https://people.idsia.ch/~juergen/)
img: learn_acf.png

title: [Adversarial Robustness of Program Synthesis Models](https://openreview.net/forum?id=17C-dfA5X69)
authors: [Mrinal Anand](https://kaiyon07.github.io/), [Pratik Kayal](https://scholar.google.com/citations?user=WOrOyp4AAAAJ), [Mayank Singh](https://mayank4490.github.io/)
img: adv_robust.png

title: [Synthesizing Video Trajectory Queries](https://openreview.net/forum?id=HyTIeooyV2H)
authors: Stephen Mell, [Favyen Bastani](https://favyen.com/), [Stephan Zdancewic](https://www.cis.upenn.edu/~stevez/), [Osbert Bastani](https://obastani.github.io/)
img: traj_query.png

title: [AutumnSynth: Synthesis of Reactive Programs with Structured Latent State](https://openreview.net/forum?id=Qw8eyl2_N_-)
authors: [Ria Das](https://www.csail.mit.edu/person/ria-das), [Joshua B. Tenenbaum](http://web.mit.edu/cocosci/josh.html), [Armando Solar-Lezama](https://people.csail.mit.edu/asolar/), [Zenna Tavares](http://www.zenna.org/)
img: autumn_synth.png

title: [Towards Neural Functional Program Evaluation](https://openreview.net/forum?id=pFy0jbqiCDY)
authors: [Torsten Scholak](https://tscholak.github.io/), [Jonathan Pilault](https://scholar.google.com/citations?user=PNFW8HwAAAAJ), [Joey Velez-Ginorio](https://www.seas.upenn.edu/~joeyv/)
img: neural_fp.png

title: [A Genetic Programming Approach To Zero-Shot Neural Architecture Ranking](https://openreview.net/forum?id=xuVVuLcqBP5)
authors: [Yash Akhauri](https://akhauriyash.github.io/), [Juan Pablo Munoz](https://www.intel.ca/content/www/ca/en/research/researchers/j--pablo-munoz.html), [Ravishankar Iyer](https://scholar.google.com/citations?user=2rO3ZvEAAAAJ), [Nilesh Jain](https://scholar.google.com/citations?user=sWUGELEAAAAJ)
img: genetic_programming.png

title: [Learning compositional programs with arguments and sampling](https://openreview.net/forum?id=KUWjNgo2Gm9)
authors: [Giovanni De Toni](https://detoni.me/), [Luca Erculiani](https://scholar.google.com/citations?user=kzYHFWMAAAAJ), [Andrea Passerini](https://disi.unitn.it/~passerini/)
img: learn_cps.png

title: [Are Transformers All That Karel Needs?](https://openreview.net/forum?id=qGDIkNmWydG)
authors: Abhay Garg, [Anand Sriraman](https://scholar.google.co.in/citations?user=3NjtqroAAAAJ), Kunal Pagarey, [Shirish Karande](https://scholar.google.co.in/citations?user=LeHCh80AAAAJ)
img: karel.png

title: [Augmenting Classic Algorithms with Neural Components for Strong Generalisation on Ambiguous and High-Dimensional Data](https://openreview.net/forum?id=_Y4FQu1aJ1Z)
authors: [Imanol Schlag](https://ischlag.github.io/about/), [Jürgen Schmidhuber](https://people.idsia.ch/~juergen/)
img: neural_aug.png

title: [Staged compilation of tensor expressions](https://openreview.net/forum?id=5TCfWXk2waG)
authors: [Marco Zocca](https://github.com/ocramz)
img: staging.png

title: [Safe Neurosymbolic Learning with Differentiable Symbolic Execution](https://openreview.net/forum?id=ZtyvT0aHNBP)
authors: [Chenxi Yang](https://cxyang1997.github.io/), [Swarat Chaudhuri](https://www.cs.utexas.edu/~swarat/)
img: safe_nsl.png

title: [Proof Extraction for Logical Neural Networks](https://openreview.net/forum?id=Xw3kb6UyA31)
authors: [Thabang Lebese](https://scholar.google.com/citations?user=0mF32KgAAAAJ), [Ndivhuwo Makondo](https://researcher.watson.ibm.com/researcher/view.php?person=ibm-Ndivhuwo.Makondo), [Cristina Cornelio](https://scholar.google.com/citations?user=EP9lmrcAAAAJ), [Naweed Khan](https://scholar.google.com/citations?user=CXaTRZUAAAAJ)
img: proof_extract.png

title: [AutoCoder: Leveraging Transformers for Automatic Code Synthesis](https://openreview.net/forum?id=fIU0j2MXTIa)
authors: [Mrinal Anand](https://kaiyon07.github.io/), [Pratik Kayal](https://scholar.google.com/citations?user=WOrOyp4AAAAJ), [Mayank Singh](https://mayank4490.github.io/)
img: autocoder.png

title: [Learning C to x86 Translation: An Experiment in Neural Compilation](https://openreview.net/forum?id=444ug_EYXet) / [code](https://github.com/jordiae/neural-compilers)
authors: [Jordi Armengol-Estapé](https://scholar.google.es/citations?user=CiHoJfcAAAAJ), [Michael O'Boyle](http://www.dcs.ed.ac.uk/home/mob/)
img: c2x86_trans.png
""".trimIndent()

papers.split(Regex("\n\n")).forEachIndexed { i, lines ->
  val leftPad = i.toString().padStart(2, '0')
  File("paper_$leftPad.md").writeText(
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