<?php
echo "Estoy donde es";
    $json = '["memito", "danielito", "alejandra", "chovi"]';
    $json1 = '["memito", "danielito", "alejandra", "chovi"]';
    $data = json_decode($json);
    echo "<br>".$data[2];
?>