(ns app.thread-first-last)

(defn print-name [name] (str "Hello, " name))

;(println (print-name "Rafa"))

;thread-first
(-> (print-name "Rafael")
    (println))


(def names ["Rafa" "Mary" "Jim" "John"])

;(println (map print-name names))

;thread-last
(->> (map print-name names)
     (println))