package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FarmRepository farmRepository;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository    the crop repository
   * @param farmRepository    the farm repository
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmRepository farmRepository,
      FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.farmRepository = farmRepository;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create crop.
   *
   * @param farmId  the farm id
   * @param newCrop the new crop
   * @return the crop
   * @throws FarmNotFoundException the farm not found exception
   */
  public Crop create(Long farmId, Crop newCrop) throws FarmNotFoundException {
    Farm farm = farmRepository.findById(farmId).orElseThrow(FarmNotFoundException::new);

    newCrop.setFarm(farm);
    return cropRepository.save(newCrop);
  }

  /**
   * Find all by farm id list.
   *
   * @param farmId the farm id
   * @return the list
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Crop> findAllByFarmId(Long farmId) throws FarmNotFoundException {
    Farm farm = farmRepository.findById(farmId).orElseThrow(FarmNotFoundException::new);

    return cropRepository.findByFarmId(farmId);
  }

  public List<Crop> findAll() {
    return cropRepository.findAll();
  }

  /**
   * Find by id crop.
   *
   * @param cropId the crop id
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop findById(Long cropId) throws CropNotFoundException {
    return cropRepository.findById(cropId).orElseThrow(CropNotFoundException::new);
  }

  public List<Crop> findAllByHarvestDateRange(LocalDate start, LocalDate end) {
    return cropRepository.findAllByHarvestDateBetween(start, end);
  }

  /**
   * Sets crop fertizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public void setCropFertizer(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop cropDb = findById(cropId);
    Fertilizer fertilizerDb = fertilizerService.findById(fertilizerId);

    cropDb.getFertilizers().add(fertilizerDb);
    fertilizerDb.getCrops().add(cropDb);

    cropRepository.save(cropDb);
  }

  /**
   * Find all fertilizers by crop id list.
   *
   * @param cropId the crop id
   * @return the list
   * @throws CropNotFoundException the crop not found exception
   */
  public List<Fertilizer> findAllFertilizersByCropId(Long cropId) throws CropNotFoundException {
    Crop cropDb = findById(cropId);

    return cropDb.getFertilizers();
  }
}
