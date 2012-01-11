(defproject backtype/dfs-datastores-cascading "1.1.0"
  :java-source-path "src/jvm"
  :source-path "src/clj"
  :java-test-path "test/jvm"
  :javac-options {:debug "true" :fork "true"}
  :repositories {"conjars" "http://conjars.org/repo"}
  :dependencies [[backtype/dfs-datastores "1.1.0-SNAPSHOT"]
                 [cascading/cascading-hadoop "2.0.0-wip-184"
                  :exclusions [org.codehaus.janino/janino
                               org.apache.hadoop/hadoop-core]]]
  :dev-dependencies [[org.apache.hadoop/hadoop-core "0.20.2-dev"]
                     [junit/junit "3.8.2"]])
