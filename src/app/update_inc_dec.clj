(ns app.update-inc-dec)

(def prices [30 100 500 1000])

(println "Increase first:" (update prices 0 inc))
(println "Decrease third:" (update prices 2 dec))

(def developer {:name "Rafael" :occupation "Programmer" :age 32})

(println "Former age:" (:age developer))
(println "New age:" (:age (update developer :age inc)))

(println "Former name:" (:name developer))
(println "New name:" (:name (update developer
                                    :name #(if (= "Rafael" %) "Rafa" %))))