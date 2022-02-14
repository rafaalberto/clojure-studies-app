(ns app.immutability)

(def founders '("Argentina" "Brazil" "Paraguay" "Uruguay"))
(def current-members (cons "Venezuela" founders))

(println "Founders: " founders)
(println "Current members: " current-members)

(println "Are they identical?" (identical? founders (rest current-members)))