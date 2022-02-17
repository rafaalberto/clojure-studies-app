(ns app.arity-overloading)

(defn hello
  ([name]
   (str "Name: " name))
  ([name last-name]
   (str "Name: " name " - Last name: " last-name))
  ([name last-name occupation]
   (str "Name: " name " - Last name: " last-name " - Occupation: " occupation)))

(println (hello "Rafael"))
(println (hello "Rafa" "Alberto"))
(println (hello "Rafa" "Alberto" "Developer"))