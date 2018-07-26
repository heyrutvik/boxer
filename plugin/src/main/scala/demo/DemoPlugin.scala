package demo

import scala.tools.nsc.Global
import scala.tools.nsc.plugins.{Plugin, PluginComponent}
import scala.tools.nsc.transform.{Transform, TypingTransformers}

class DemoPlugin(val global: Global) extends Plugin {

  val name = "lazyval-optimizer-plugin"
  val description = "convert all lazy val to optimized version"
  val components = List[PluginComponent](LazyvalOptComponent)

  private object LazyvalOptComponent extends PluginComponent with Transform with TypingTransformers {
    val global = DemoPlugin.this.global
    import global._

    override val runsAfter = List("typer")

    val phaseName = "lazyval-optimizer"

    protected def newTransformer(unit: CompilationUnit): Transformer = {
      new LazyValTransformer(unit)
    }

    class LazyValTransformer(unit: CompilationUnit) extends TypingTransformer(unit) {
      // TODO
    }
  }
}
