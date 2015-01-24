(ns scratch.core)

(def graph #{[:a :b] [:b :c] [:c :d]
               [:x :y] [:d :a] [:b :e]} )

((fn [g]
   (letfn [
    (connected? [e]
      (prn (count e))
      (prn e)
      (if (>= 1 (count e))
        true
        (let [
              v1  (ffirst e)
              ; incoming neighbours
              in  (for [ei e :when (= v1 (second ei))] (first  ei))
              ; outgoing neigbours
              out (for [ei e :when (= v1 (first  ei))] (second ei))
              e1  (filter #(not= % v1) (concat in out))]
          (prn "=======")
          (prn v1)
          (prn in)
          (prn out)
          (prn (concat in out))
          (prn e1)
          (prn "=======")
          (if (empty? e1)
            false
            (recur (remove #(some #{v1} %) e))))))
    ]
    (connected? g))
   )graph)
