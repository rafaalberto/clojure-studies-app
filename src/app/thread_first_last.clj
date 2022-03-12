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

;thread-first vs thread-last

(-> (str "Hi, ")
    (str "Rafael!")
    (println))

(->> (str "Rafael")
     (str "Hi, ")
     (println))