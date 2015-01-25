(ns scratch.core)

(def g #{[1 2]})

((fn [g]
   (letfn [(connected? [g]
             (loop [q (conj [] (ffirst g)) visited #{}]
               (if (empty? q)
                 (let [rem (filter #(not (contains? visited %)) (flatten (for [e g] e)))]
                   (prn rem)
                   (= 0 (count rem)))
                 (let [v1 (peek q)
                       edges (filter (partial some #{v1}) g)
                       vertices (filter (partial not= v1) (flatten edges))
                       unvisited (filter #(not (contains? visited %)) vertices)]
                   (recur (into (rest q) unvisited) (into (conj visited v1) unvisited))))))]
     (connected? g))) g )
