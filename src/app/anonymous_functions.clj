(ns app.anonymous-functions)

;3 different ways to create and call function
(defn print-name [name]
  (str "Hello, " name))

(println (print-name "Rafael"))
(println ((fn [name] (str "Hello, " name)) "Rafael"))
(println (#(str "Hello, " %) "Rafael"))