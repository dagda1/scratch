(ns scratch.core)

(def more-legs #{[8 4] [9 3] [4 2] [27 9]})

(letfn [(f [x]  (reduce (fn [acc curr]
                          (concat acc (reduce (fn [a1 parent]
                                                (let [sibling (first (filter #(not= parent %) curr))
                                                      res (filter #(not= curr %) x)]
                                                  (concat a1 (reduce (fn [a2 c]
                                        ;(prn "c:= " c " parent:= " parent " sibling:= " sibling)
                                        ;(prn (str "a2:=" a2))
                                                                       (let [candidate [parent (first (filter #(not= sibling %) c))]]
                                                                         (if (and (some #(= sibling %) c) (not (some #(= (set %) (set candidate) ) acc)) )
                                                                           (conj a2 candidate)
                                                                           a2))
                                                                       )
                                                                     [] res))
                                                  )
                                                )
                                              [] curr))
                          ) [] x))]
  (let [o (concat (f more-legs) more-legs)
        y (set (concat o (f o) o))]
    y
    )
  )

;; #{
;;   ["cat" "snake"]
;;   ["spider" "man"]
;;   ["spider" "snake"]

;;   ["cat" "man"]
;;   ["man" "snake"]
;;   ["spider" "cat"]
;; }