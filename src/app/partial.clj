(ns app.partial)

(defn email-struct [username domain]
  (str username "@" domain))

(def build-email (partial email-struct "rafael.alberto1703"))
(println "Email:" (build-email "gmail.com"))