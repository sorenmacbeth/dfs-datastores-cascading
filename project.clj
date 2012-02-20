(defproject backtype/dfs-datastores-cascading "1.1.1"
  :java-source-path "src/jvm"
  :source-path "src/clj"
  :java-test-path "test/jvm"
  :javac-options {:debug "true" :fork "true"}
  :javac-source-path [["src"] ["test"]]
  :junit [["classes"]]
  :junit-options {:fork "off" :haltonfailure "on"}
  :repositories {"conjars" "http://conjars.org/repo"}
  :dependencies [[backtype/dfs-datastores "1.1.0"]
                 [cascading/cascading-hadoop "2.0.0-wip-184"
                  :exclusions [org.codehaus.janino/janino
                               org.apache.hadoop/hadoop-core]]]
  :dev-dependencies [[org.apache.hadoop/hadoop-core "0.20.2-dev"]
                     [lein-javac "1.3.0"]
                     [lein-junit "1.0.0"]
                     [junit "4.7"]])
