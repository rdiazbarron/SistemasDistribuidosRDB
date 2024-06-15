<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Expositor>
 */
class ExpositorFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'codigo'=>$this->faker->numberBetween(1,100),
            'titulo'=>$this->faker->text(30),
            'nombre_expositor'=>$this->faker->name(),
        ];
    }
}
