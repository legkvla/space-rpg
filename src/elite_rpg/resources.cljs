(ns elite-rpg.resources
  (:require [elite-rpg.logging :as log]))

(def resources
  {
   :ship "/assets/galaga32.png"})

(defn load-resources [on-ready-fn]
  (try
    (let [l js/PIXI.loader]
      (mapv #(.add l %) (vals resources))
      (.load l
        (fn [loader resources]
          (log/info "Resources loaded: " resources)
          (on-ready-fn loader))))
    (catch js/Object e
      (do (log/warn (str e))
        (on-ready-fn js/PIXI.loader)))))

(defn get-texture [res-id]
  (-> js/PIXI.loader
    .-resources
    (aget (res-id resources))
    .-texture))

(defn lookup-file [file-id] (file-id resources))
