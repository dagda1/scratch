(ns scratch.lcm)

(take 2 ((fn [lst]
    (letfn [(process [curr]
              (let [row (vec (concat
                              (cons (first curr)
                                    (map #(apply + %)
                                         (partition 2 1 curr)
                                         )) [(last curr)]))]
                (lazy-seq
                 (cons curr (process row)))))]
      (process lst))
    ) [3 1 2]))

