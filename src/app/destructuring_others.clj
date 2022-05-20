(ns app.destructuring-others)

(def engineers [{:name "Mary" :age 23}
                {:name "John" :age 21}
                {:name "Jonas" :age 45}])

(map #(println "Name: " (:name %)) engineers)

(def result {:language {:english    "Learning Clojure"
                        :portuguese "Aprendendo Clojure"}})

(println "First way:" (->> result :language :english))