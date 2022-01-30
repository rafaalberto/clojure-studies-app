(ns app.set)

(defn print-names [names]
  (println "Name: " names))

(def names #{"Rafa" "John" "Mary"})
;(def names (hash-set "Rafa" "John" "Mary")) ; another way to declare hash-set

(println "add new name: " (conj names "Peter"))

;immutable - the last item added is not here
(map print-names names)