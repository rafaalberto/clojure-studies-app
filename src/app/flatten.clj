(ns app.flatten)

(def numbers '(1 2 3 [3 4 1]))

(def items (set (flatten numbers)))

(println items)

(->> (flatten numbers)
     (set)
     (println "Thread-last"))