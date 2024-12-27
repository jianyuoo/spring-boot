package air.admin.spring_boot.base.cases.controller;


import air.admin.spring_boot.base.cases.dto.CasesQueryDto;
import air.admin.spring_boot.base.cases.dto.CasesupdateDto;
import air.admin.spring_boot.base.cases.entity.Cases;
import air.admin.spring_boot.base.cases.service.CasesService;
import air.admin.spring_boot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cases")
public class CasesController {

    @Autowired
    private CasesService casesService;

    @PostMapping("/add")
    public Result add(@RequestBody Cases dto) {
        return casesService.add(dto);
    }

    @GetMapping("/delete")
    public Result delete(@RequestBody CasesQueryDto dto) {
        return casesService.delete(dto);
    }

    @PostMapping("/update")
    public Result update(@RequestBody CasesupdateDto dto) {
        return casesService.update(dto);
    }

    @PostMapping("/select")
    public Result select(@RequestBody CasesQueryDto dto) {
        return casesService.get(dto);
    }

}
