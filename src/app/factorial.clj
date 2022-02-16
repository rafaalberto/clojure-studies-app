(ns app.factorial)

(defn factorial [number]
  (if (> number 1)
    (* number (factorial (- number 1)))
    number))

(def factorial-number 5)
(def result (str "Factorial of " factorial-number " is " (factorial factorial-number)))
(println result)

