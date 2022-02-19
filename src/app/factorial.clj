(ns app.factorial)

(defn factorial [number]
  (prn "Factorial to calculate: " number)
  (if (> number 1)
    (* number (factorial (- number 1)))
    number))

(def factorial-number 5)
(def result (str "Factorial of " factorial-number
                 " is " (factorial factorial-number)))
(println result)


(defn recur-factorial
  ([number]
   (recur-factorial 1 number))
  ([total number]
   (prn "total: " total " - number: " number)
   (if (= number 1)
     total
     (recur (* total number) (- number 1)))))

(println "Factorial is" (recur-factorial 6N))