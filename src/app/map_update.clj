(ns app.map-update)

(def stock {:backpack 10 :t-shirt 3})

(println "Stock:" stock)

(println "Increase:" (update stock :backpack inc))

(println "Assoc:" (assoc stock :headset 1))

(println "Update value: "
         (update stock :t-shirt #(+ % 10)))