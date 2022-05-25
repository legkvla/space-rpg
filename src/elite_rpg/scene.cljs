(ns elite-rpg.scene
  (:require
    [elite-rpg.sprites :as sprites]))

(def a:ship-x (atom 370))

(defn build-scene [app]
  (sprites/add-sprite app :ship1 :ship @a:ship-x 500 64 64))

(defn on-input [code down?])

(defn on-tick [delta]
  (when-let [ship (sprites/get-sprite :ship1)]
    (swap! a:ship-x inc)
    (set! (.-position.x ship) @a:ship-x)))
