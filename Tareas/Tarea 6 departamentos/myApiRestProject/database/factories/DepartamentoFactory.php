<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Departamento>
 */
class DepartamentoFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $departamentos = [
            'Chuquisaca' => 'Sucre',
            'Potosi' => 'Potosi',
            'Oruro' => 'Oruro',
            'La Paz' => 'La Paz',
            'Cochabamba' => 'Cochabamba',
            'Santa Cruz' => 'Santa Cruz',
            'Beni' => 'Trinidad',
            'Pando' => 'Cobija',
            'Tarija' => 'Tarija'
        ];
    
        $departamento = $this->faker->randomElement(array_keys($departamentos));
    
        return [
            'nombre' => $departamento,
            'descripcion' => $this->faker->text,
            'codigo' => $this->faker->unique()->randomNumber(5),
            'capital' => $departamentos[$departamento],
            'superficie' => $this->faker->randomFloat(2, 100, 10000),
        ];
    }
}
