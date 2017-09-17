package scala212.src_incompat

class PackagePrivate {
  private[src_incompat] def method(str: String): String = str

  def method(str: String, int: Int): String = str
}
