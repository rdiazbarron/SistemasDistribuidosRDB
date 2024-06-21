<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Temps>
 */
class TempsFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            //rotected $fillable = ['fecha','temperatura'];
        
            'fecha'=>$this->faker->date(),
            'temperatura'=>$this ->faker->numberBetween(20,26),
        ];
    }
}
