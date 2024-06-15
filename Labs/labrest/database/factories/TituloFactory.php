<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Titulo>
 */
class TituloFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        //protected $fillable = ['ci','nombre','apellido_paterno','apellido_materno','titulo'];
        return [
            
            'ci'=>$this->faker->numberBetween(1000000,1001000),
            'nombre'=>$this->faker->name(),
            'apellido_paterno'=>$this->faker->name(),
            'apellido_materno'=>$this->faker->name(),
            'titulo'=>$this->faker->text(30),
        ];
    }
}
