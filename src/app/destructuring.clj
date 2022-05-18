(ns app.destructuring)

(def multiplayer-game
  {:joe {:score 100}})

(defn print-players []
  (let [{joe-player :joe} multiplayer-game]
    (str "Joe Player: " joe-player)))

(println (print-players))

(defn print-scores []
  (let [{{joe-score :score} :joe} multiplayer-game]
    (str "Joe Score: " joe-score)))

(println (print-scores))

(def engineers [{:name "Mary" :age 23}
                {:name "John" :age 21}
                {:name "Jonas" :age 45}])

(map #(println "Name: " (:name %)) engineers)