(defproject backtype/dfs-datastores-cascading "1.1.0"
  :java-source-path "src/jvm"
  :javac-options {:debug "true" :fork "true"}
  :repositories {"conjars" "http://conjars.org/repo"}
  :dependencies [[backtype/dfs-datastores "1.0.5"]
                 [cascading/cascading-hadoop "2.0.0-wip-166"
                  :exclusions [org.codehaus.janino/janino
                               org.apache.hadoop/hadoop-core
                               thirdparty/jgrapht-jdk1.6
                               riffle/riffle]]
                 [thirdparty/jgrapht-jdk1.6 "0.8.1"]
                 [riffle/riffle "0.1-dev"]]
  :dev-dependencies [[org.apache.hadoop/hadoop-core "0.20.2-dev"]
                     [junit/junit "3.8.2"]])
